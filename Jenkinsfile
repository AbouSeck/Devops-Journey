pipeline {
    agent {label 'centos'}
    
    tools {
        maven "Maven 3"
    }

    stages {
        stage('Build') {
            steps {
                echo "*******-Starting CI CD Pipeline Tasks-*******"
                echo "#-BUILD"
                echo ""
                echo "..... Build Phase Started :: Compiling REST API Source Code :: ......"
                sh '''
                    cd Springboot_crud_webapp/rest
                    mvn clean install
                '''
            }
        }
    }
}
