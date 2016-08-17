// Constants
def BuildPipelineFolderName= "/${PROJECT_NAME}"

// Jobs
def generateBuildPipelineView = buildPipelineView(BuildPipelineFolderName + "/Build_Pipeline_View")
def generateBuildJob = freeStyleJob(BuildPipelineFolderName + "/Build_Java_Project")

generateBuildJob.with {
	scm {
			git {
					remote {
							url("git@gitlab:${PROJECT_NAME}.git")
							credentials('adop-jenkins-master')
					}
					branch('refs/heads/$gitlabBranch')
			}
	}
	wrappers {
		preBuildCleanup()
        timestamps()
    }
	configure { project ->
				project / 'triggers' / 'com.dabsquared.gitlabjenkins.GitLabPushTrigger' {
					spec('')
					triggerOnPush('true')
					triggerOnMergeRequest('false')
					triggerOpenMergeRequestOnPush('never')
					ciSkip('true') 	
					setBuildDescription('true')
					addNoteOnMergeRequest('true')
					addVoteOnMergeRequest('true')
					addCiMessage('false')
					addVoteOnMergeRequest('true')
					branchFilterType('All')
					includeBranchesSpec('')
					excludeBranchesSpec('')
					targetBranchRegex('')
					acceptMergeRequestOnSuccess('false')
				}
	}
	steps {
			maven {
				mavenInstallation('ADOP Maven')
				goals('''
clean
package
				''')
			}
	}
	configure { project ->
			project / 'publishers' / 'com.dabsquared.gitlabjenkins.publisher.GitLabCommitStatusPublisher' {}
	}
}