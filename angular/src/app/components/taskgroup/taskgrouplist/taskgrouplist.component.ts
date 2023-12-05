import {Component, OnInit} from '@angular/core';
import {TaskGroupDto} from "../../../model/taskgroup/task-group-dto";
import {TaskGroupService} from "../../../service/taskgroup/task-group.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../service/auth/auth.service";
import {UserResponseDto} from "../../../model/user/user-response-dto";
import {UserService} from "../../../service/user/user.service";
import {CreateTaskGroupRequest} from "../../../model/taskgroup/create-task-group-request";

@Component({
  selector: 'app-taskgrouplist',
  templateUrl: './taskgrouplist.component.html',
  styleUrls: ['./taskgrouplist.component.css']
})
export class TaskgrouplistComponent implements OnInit {
  taskGroups: TaskGroupDto[] = [];
  email: string = '';
  userId: number = 0;
  visibleTaskLists: number[] = [];
  user: UserResponseDto | null = null;
  newGroup: CreateTaskGroupRequest = {
    name: '',
    userId: this.authService.getLoggedInUserId() || 0
  };
  showNewTaskInput: boolean = false;

  constructor(private taskGroupService: TaskGroupService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.getUserData();
    this.route.params.subscribe((params) => {
      const userId = +params['userId'];
      const email = params['email'];
      if (userId) {
        this.loadTaskGroupsByUserId(userId);
      } else if (email) {
        this.loadTaskGroupsByUserEmail(email);
      }
    });
  }


  // Load taskGroups by userId
  loadTaskGroupsByUserId(userId: number) {
    this.taskGroupService.getAllTaskGroupsByCurrentUser(userId).subscribe(
      (taskGroups: TaskGroupDto[]) => {
        this.taskGroups = taskGroups;
      },
      (error) => {
        console.error('Error loading taskGroups:', error);
      }
    );
  }

  // Load taskGroups by User Email
  loadTaskGroupsByUserEmail(email: string) {
    this.taskGroupService.getAllTaskGroupsByCurrentUserE(email).subscribe(
      (taskGroups: TaskGroupDto[]) => {
        this.taskGroups = taskGroups;
      },
      (error) => {
        console.error('Error loading taskGroups:', error);
      }
    );
  }

  // Open task group
  toggleTaskList(taskGroupId: number): void {
    const index = this.visibleTaskLists.indexOf(taskGroupId);
    if (index === -1) {
      this.visibleTaskLists.push(taskGroupId);
    } else {
      this.visibleTaskLists.splice(index, 1);
    }
  }

  // Open task group
  isTaskListVisible(taskGroupId: number): boolean {
    return this.visibleTaskLists.includes(taskGroupId);
  }

  getUserData() {
    const userId = this.authService.getLoggedInUserId();

    if (userId) {
      this.userService.getUserById(userId).subscribe(
        (userData: UserResponseDto) => {
          this.user = userData;
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
    }
  }

  // Go to User Profile
  goToUserProfile() {
    const userId = this.authService.getLoggedInUserId();
    this.router.navigate(['api/user/me', this.userId]);
  }

  toggleNewTaskInput() {
    this.showNewTaskInput = !this.showNewTaskInput;
    if (!this.showNewTaskInput) {
      this.newGroup.name = '';
    }
  }


  // Create new TaskGroup
  createTaskGroup() {
    const userId = this.authService.getLoggedInUserId();

    if (userId !== null) {
      const request: CreateTaskGroupRequest = {
        name: this.newGroup.name,
        userId: userId,
      };

      this.taskGroupService.createTaskGroup(request).subscribe(
        (response) => {
          this.loadTaskGroupsByUserId(userId)
          console.log('TaskGroup created successfully', response);
          this.clearForm();
          this.showNewTaskInput = false;
        },
        (error) => {
          console.error('Cant create task group', error);
        }
      );
    } else {
      console.error('Cant find user by userID.');
    }
  }

  // Clear From for inputting TaskGroup name
  clearForm() {
    this.newGroup.name = '';
  }

  // Delete taskGroup
  deleteTaskGroup(id: number) {
    this.taskGroupService.deleteTaskGroupById(id).subscribe(
      () => {
        this.loadTaskGroupsByUserId(this.userId)
      },
      (error) => {
        console.error('Error deleting taskGroup:', error);
      }
    )
  }

}
