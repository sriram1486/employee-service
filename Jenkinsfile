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
	 
      stage('Docker Build') {
       agent any
       steps {
         sh 'docker build -t shanem/spring-petclinic:latest .'
       }	
      }	      
     stage('Email Notification') {
     	 mail bcc: '', body: 'Delivery jar file is done',
	cc: '', from: '', replyTo: '', subject: 'Jenkins job', to: 'sriramulareddy@gmail.com'
    }
    }
}
