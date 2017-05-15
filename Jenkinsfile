
node {
	stage "Checkout Code"

	checkout scm

	stage "Prepare Environment"

	try {
	
		sh '''
		docker run -d --name mysql-$JOB_NAME-$BUILD_NUMBER \\
	      -v $(pwd)/provider/src/test/resources/database:/docker-entrypoint-initdb.d/ \\
	      -v $(pwd):/data \\
	      -e MYSQL_ROOT_PASSWORD=1234 \\
	      -w /data \\
	      mysql:5.6
		'''

		stage "Wait for Environment"

		sh '''
		docker run --rm \\
			--link mysql-$JOB_NAME-$BUILD_NUMBER:database \\
			jwilder/dockerize dockerize -wait tcp://database:3306 -timeout 60s
		'''

		stage "Run Tests"

		sh '''
		docker run -i -u $(id -u jenkins) --name maven-$JOB_NAME-$BUILD_NUMBER \\
	      --link mysql-$JOB_NAME-$BUILD_NUMBER:database \\
	      -v /home/jenkins/.m2:/home/jenkins/.m2 \\
	      -v $(pwd)/provider:/data \\
	      -w /data \\
	      maven-jenkins:3.5.0-jdk-8 mvn clean verify -Dspring.profiles.active=itest
		'''

        // config with standar maven docker image:
		// maven:3.5.0-jdk-8 mvn clean verify -Dspring.profiles.active=itest -Duser.home=/home/jenkins

	} finally {

		stage "Tear Down"

		sh "docker stop mysql-$JOB_NAME-$BUILD_NUMBER || true "
	    sh "docker stop maven-$JOB_NAME-$BUILD_NUMBER || true "
	    sh "docker rm mysql-$JOB_NAME-$BUILD_NUMBER || true "
	    sh "docker rm maven-$JOB_NAME-$BUILD_NUMBER || true "
	}
}