package com.gulshan.taskmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gulshan.taskmanager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gulshan.taskmanager.repository.TaskRepository;
import com.gulshan.taskmanager.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

	@Autowired
	private TaskRepository taskRepo;

	// Get all tasks
	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	// Create a new task
	@PostMapping("/tasks")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		Task t = taskRepo.save(task);
		return new ResponseEntity<>(t, HttpStatus.CREATED);
	}

	// Get task by ID
	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Task task = taskRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	// Update a task
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
		Task task = taskRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));

		task.setTitle(taskDetails.getTitle());
		task.setDescription(taskDetails.getDescription());
		task.setDueDate(taskDetails.getDueDate());
		task.setCategory(taskDetails.getCategory());
		task.setCompleted(taskDetails.isCompleted());

		Task updatedTask = taskRepo.save(task);
		return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}

	// Delete a task
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id) {
		Task task = taskRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));

		taskRepo.delete(task);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Get tasks by category
	@GetMapping("/tasks/category")
	public List<Task> getTasksByCategory(@RequestParam String category) {
		return taskRepo.findByCategory(category);
	}

	// Mark a task as completed
	@PutMapping("/tasks/{id}/completed")
	public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long id) {
		Task task = taskRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));

		task.setCompleted(true);
		Task updatedTask = taskRepo.save(task);
		return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}

	// Search tasks
	@GetMapping("/tasks/search")
	public List<Task> searchTasks(@RequestParam("query") String query) {
		return taskRepo.findByTitleContainingIgnoreCase(query);
	}
}
