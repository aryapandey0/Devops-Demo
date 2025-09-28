pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "arya/hello-world-app:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/aryapandey0/Devops-Demo.git'
            }
        }

        stage('Build with Maven') {
            steps {
                echo "🔨 Building Spring Boot app..."
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo "🐳 Building Docker image..."
                bat "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        

        stage('Deploy') {
            steps {
                echo "🚀 Deploying Docker container..."
                bat 'docker stop hello-world-app || true'
                bat 'docker rm hello-world-app || true'
                bat "docker run -d --name hello-world-app -p 8080:8080 $USER/hello-world-app:latest"
            }
        }
    }
}
