# Sample Job for GitLab Merge Requests

This is a sample Jenkins job which is triggered when there is a merge request event in your GitLab project. This will also send the build status of your job to your GitLab.

Pre-requisites:
	Jenkins (version 1.609.1+)
		1. GitLab Plugin
		2. Job DSL Plugin
	GitLab (version 8.1+)
# Setting up Jenkins and GitLab

## Jenkins

### Using the Job DSL Script
	
	If you want to load the Job DSL script automatically, create a Job in your Jenkins instance that will clone this repo and process Job DSL's.


### Creating your own Build Job

	Create a Jenkins job and tick the "Build when a change is pushed to GitLab" Build Trigger. Next, make sure that the checkbox for the "Build on Merge Request Events" is also checked.

	Take note of the "GitLab CI Service URL" as well. You will need it on setting up your GitLab webhook.
	
	On the post-build events, Add the "Publish build status to GitLab commit" step.
	

## GitLab
	For the build job to be triggered when a Merge Request event occurs, you need to setup your GitLab project's webhooks.
	
	Go to your Project's page > Settings > Webhooks.
	
	On the URL, insert the "GitLab CI Service URL" that is on your Jenkins Build Job earlier. On the webhook triggers, check the "Merge Request events" box.