pipeline {
    agent {label 'centos'}

    stages {
        stage('Build') {
            steps {
                sh 'echo "*******-Starting CI CD Pipeline Tasks-*******"'
                sh 'echo "#-BUILD"'
                sh 'echo ""'
                sh 'echo "..... Build Phase Started :: Compiling REST API Source Code :: ......"'
                sh 'cd SpringBoot\ CRUD\ with\ REST\ Api/rest'
                sh 'mvn clean install'
                sh 'cd ../webapp'
                sh 'mvn clean install'
            }
        }
    }
}
