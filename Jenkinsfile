stage "Checkout Code"

checkout scm

stage "Prepare Environment"
sh "echo $BUILD_NUMBER"
sh "docker run -d --name mysql-$bamboo_buildResultKey \
          -v $bamboo_working_directory/provider/src/test/resources/database:/docker-entrypoint-initdb.d/ \
          -v $bamboo_working_directory:/data \
          -e MYSQL_ROOT_PASSWORD=1234 \
          -w /data \
          mysql"

stage "Run Tests"

sh " docker run -i --name maven-$bamboo_buildResultKey \
          --link mysql-$bamboo_buildResultKey:database \
          -v /vagrant/.m2:/root/.m2 \
          -v $bamboo_working_directory/provider:/data \
          -w /data \
          maven:3.3.9-jdk-8 mvn clean verify -Dspring.profiles.active=itest "

stage "Tear Down"

sh "docker stop mysql-$bamboo_buildResultKey
          docker stop maven-$bamboo_buildResultKey
          docker rm mysql-$bamboo_buildResultKey
          docker rm maven-$bamboo_buildResultKey"