package com.micky.gradle;

import org.gradle.api.*
import org.gradle.api.tasks.TaskAction;

class MyCustomPlugin implements Plugin<Project> {
	void apply(Project project) {
		println "Hi this is micky's plugin"
		project.task('myTask').doLast {
			println "Hi this is micky's plugin"
		}
	}

	@TaskAction
	void output() {
		println "Hello this is my custom task output"
	}
}

class CleanerPlugin implements Plugin<Project>{
	@Override
	void apply(Project project) {
		project.afterEvaluate {
			project.android.lintOptions.xmlOutput = new File(project.buildDir, "lintResult.xml")
		}
		project.tasks.create('cleanTest', CleanTestTask)
	}
}

class CleanTestTask extends DefaultTask{
	CleanTestTask() {
		super()
		dependsOn "lint"
	}
	@TaskAction
	def testClean(){
		System.out.println("==================")
		System.out.println("Test Clean Task")
		System.out.println("==================")
	}
}
