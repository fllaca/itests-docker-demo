
node {
	stage "Checkout Code"

	checkout scm

	stage "Prepare Environment"
	
	sh 
	'''
	docker run -d --name mysql-$JOB_NAME-$BUILD_NUMBER \\
      -v $(pwd)/provider/src/test/resources/database:/docker-entrypoint-initdb.d/ \\
      -v $(pwd):/data \\
      -e MYSQL_ROOT_PASSWORD=1234 \\
      -w /data \\
      mysql
	'''

	stage "Wait for Environment"

	sh
	'''
	docker run --rm -it \\
		--link mysql-$JOB_NAME-$BUILD_NUMBER:database \\
		jwilder/dockerize dockerize -wait tcp://database
	'''

	stage "Run Tests"

	sh 
	'''
	docker run -i --name maven-$JOB_NAME-$BUILD_NUMBER \\
      --link mysql-$JOB_NAME-$BUILD_NUMBER:database \\
      -v /home/jenkins/.m2:/root/.m2 \\
      -v $(pwd)/provider:/data \\
      -w /data \\
      maven:3.3.9-jdk-8 mvn clean verify -Dspring.profiles.active=itest 
	'''

	stage "Tear Down"

	sh 
	'''
	docker stop mysql-$JOB_NAME-$BUILD_NUMBER
    docker stop maven-$JOB_NAME-$BUILD_NUMBER
    docker rm mysql-$JOB_NAME-$BUILD_NUMBER
    docker rm maven-$JOB_NAME-$BUILD_NUMBER
    '''
}