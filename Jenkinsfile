pipeline {
   agent {
       docker {
           image 'maven:3-alpine'
           args '-v /root/.m2:/root/.m2'
       }
   }

   stages {
       stage('Build') {
           steps {
               echo "Building"
               sh 'mvn -f plag-detector/pom.xml compile'
               sh 'mvn -f plag-detector/pom.xml package'
           }
       }
       stage('Test'){
           steps {
               echo "Testing"
               sh 'mvn -f plag-detector/pom.xml test'
           }
       }
    }
   
   stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                        sh 'mvn -f plag-detector/pom.xml clean install'
                        sh 'mvn -f plag-detector/pom.xml sonar:sonar'
                }
            }
        }

}


