package com.mosbach.demo.model.task;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TokenTask {

	private String token;
	private Task task;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	public TokenTask() { }

	public TokenTask(Task task, String token) {
		this.task = task;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}







	/*
	@SuppressWarnings("deprecation")
	public void addTask(Task task) {
		// TaskManager taskManager = PropertyFileTaskManagerImpl.getPropertyFileTaskManagerImpl("src/main/resources/TaskList.properties");
		TaskManager taskManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		// taskManager.addTask(task, new Student("me", "me"));

		// Region euCentral = Region.getRegion(Regions.US_EAST_1);
		// sqs.setRegion(euCentral);
        // .withDelaySeconds(1);    
		
		AWSCredentials awsCredentials = new SimpleAWSCredentials();
		AmazonSQS sqs = new AmazonSQSClient(awsCredentials);
		
		SendMessageRequest send_msg_request = new SendMessageRequest()
		        .withQueueUrl("https://sqs.us-east-1.amazonaws.com/887927861730/Mosbach-task-organizer-Created-new-task")
		        .withMessageBody("Added the following task: " + task.getName() + " with priority: " + task.getPriority());
		sqs.sendMessage(send_msg_request);

		
		
	}
*/

}