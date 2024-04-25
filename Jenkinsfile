pipeline {
  agent any
    tools {
      maven 'maven3'
                 jdk 'JDK17'
    }
    stages {      
        stage('Build maven ') {
            steps { 
                    sh 'pwd'      
                    sh 'mvn  clean install package'
            }
        }
        
        stage('Copy Artifact') {
           steps { 
                   sh 'pwd'
		   sh 'cp -r target/*.jar docker'
           }
        }
	 
        stage('Build docker image') {
           steps {
               script {         
                 def customImage = docker.build('sriram1406/employee-service', "./docker")
                 docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                 customImage.push("${env.BUILD_NUMBER}")
                 }                     
           }
        }
	  }
       stage('Email Notification') {
	  mail bcc: '', body: 'Delivery jar file is done',
	     cc: '', from: '', replyTo: '', subject: 'Jenkins job', to: 'sriramulareddy@gmail.com'
       }
    }
}
