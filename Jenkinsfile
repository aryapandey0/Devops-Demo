pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "arya/hello-world-app:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/<username>/hello-world-devops.git'
            }
        }

        stage('Build with Maven') {
            steps {
                echo "🔨 Building Spring Boot app..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo "🐳 Building Docker image..."
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                echo "⬆️ Pushing Docker image to DockerHub..."
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                    sh "docker tag hello-world-app $USER/hello-world-app:latest"
                    sh "docker push $USER/hello-world-app:latest"
                }
            }
        }

        stage('Deploy') {
            steps {
                echo "🚀 Deploying Docker container..."
                sh 'docker stop hello-world-app || true'
                sh 'docker rm hello-world-app || true'
                sh "docker run -d --name hello-world-app -p 8080:8080 $USER/hello-world-app:latest"
            }
        }
    }
}
