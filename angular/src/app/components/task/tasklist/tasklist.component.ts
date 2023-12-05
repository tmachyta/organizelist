import {Component, OnInit} from '@angular/core';
import {TaskDto} from "../../../model/task/task-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../service/auth/auth.service";
import {TaskService} from "../../../service/task/task.service";

@Component({
  selector: 'app-tasklist',
  templateUrl: './tasklist.component.html',
  styleUrls: ['./tasklist.component.css']
})
export class TasklistComponent implements OnInit {
  tasks: TaskDto[] = [];
  userEmail: string | null = null;

  constructor(private taskService: TaskService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.userEmail = this.authService.getUserEmail();

    if (this.userEmail !== null) {
      this.loadTasksByUserEmail(this.userEmail);
    }
  }

  loadTasksByUserEmail(email: string) {
    this.taskService.getAllTaskByCurrentUserEmail(email).subscribe(
      (tasks: TaskDto[]) => {
        this.tasks = tasks;
      },
      (error) => {
        console.error('Error loading tasks:', error);
      }
    );
  }
}
