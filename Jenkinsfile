pipeline {
    agent {label 'wsl'}

    stages {
        stage('Build') {
            steps {
                echo "*******-Starting CI CD Pipeline Tasks-*******"
                echo "#-BUILD"
                echo "#-BUILD REST API"
                echo ""
                echo "..... Build Phase Started :: Compiling REST API Source Code :: ......"
                sh '''
                    cd Springboot_crud_webapp/rest
                    mvn clean install -DskipTests
                '''
            }
        }
    }
}
