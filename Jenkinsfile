pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'anti1346/helloworld' // 도커 허브 레포지토리 이름
        DOCKER_IMAGE = 'helloworld' // 도커 이미지 이름
    }

    stages {
        stage('Prepare') {
            steps {
                echo 'Cloning Repository'
                git branch: 'main',
                    url: 'https://github.com/anti1346/helloworld.git',
                    credentialsId: 'github_credentials'
            }
            post {
                success {
                    echo 'Successfully Cloned Repository'
                }
                failure {
                    echo 'Failed to Clone Repository'
                }
            }
        }

        stage('Build Gradle') {
            steps {
                echo 'Building Gradle'
                dir('.') {
                    sh './gradlew clean build --exclude-task test'
                }
            }
            post {
                failure {
                    echo 'Failed to Build Gradle'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker Image'
                script {
                    DOCKER_IMAGE = docker.build DOCKER_IMAGE_NAME
                }
            }
            post {
                failure {
                    echo 'Failed to Build Docker Image'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker Image'
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub_credentials') {
                        DOCKER_IMAGE.push('latest')
                    }
                }
            }
            post {
                success {
                    echo 'Docker Image Pushed Successfully'
                    sh 'docker rmi $(docker images -q -f dangling=true)'
                }
                failure {
                    error 'Failed to Push Docker Image'
                }
            }
        }

//         stage('Remote Server Docker Pull') {
//             steps {
//                 echo 'Pulling Docker Image on Remote Server'
//                 sshPublisher(
//                     continueOnError: false,
//                     failOnError: true,
//                     publishers: [
//                         sshPublisherDesc(
//                             configName: 'remote-server',
//                             verbose: true,
//                             transfers: [
//                                 sshTransfer(
//                                     execCommand: 'sh /home/scripts/spring-container.sh'
//                                 )
//                             ]
//                         )
//                     ]
//                 )
//             }
//             post {
//                 success {
//                     echo 'Completed Remote Server Docker Pull'
//                 }
//                 failure {
//                     echo 'Failed Remote Server Docker Pull'
//                 }
//             }
//         }
    }
}
