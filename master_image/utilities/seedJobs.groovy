def gitType = System.getenv('ENVIRONMENT') == 'prod' ? 'PT_TAG' : 'PT_BRANCH_TAG'

def jobTitle = ['devops', 'seedJobs']

freeStyleJob(jobTitle.join('/')) {
    label('built-in')
    displayName('Seed Jobs - Global')
    description('Seed jobs available to Jenkins')
    logRotator {
        numToKeep(5)
        artifactNumToKeep(1)
    }
    triggers {
        hudsonStartupTrigger {
        quietPeriod('5')
        runOnChoice('ON_CONNECT')
        label('built-in')
        nodeParameterName('')
        }
    }
    parameters {
        gitParameter {
            name('BRANCH')
            defaultValue('dev')
            description('Branch or tag to use for seedJobs')
            type(gitType)
            branch('')
            branchFilter('origin/(.)')
            tagFilter('')
            sortMode('DESCENDING_SMART')
            selectedValue('NONE')
            useRepository('')
            quickFilterEnabled(true)
        }
    }
    scm {
        git {
            remote {
                github('Zak-Roz/devops', 'https')
            }
            branches('${BRANCH}')
        }
    }
    steps {
        action = System.getenv('ENVIRONMENT') == 'test' ? 'IGNORE' : 'DELETE'
        dsl {
            external('jobs/*.groovy')
            removeAction(action)
            removeViewAction(action)
        }
    }
}