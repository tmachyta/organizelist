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
      this.userId = +params['id'];
      const email = params['email'];
      if (this.userId) {
        this.loadTaskGroupsByUserId(this.userId);
      } else if (email) {
        this.loadTaskGroupsByUserEmail(email);
      }
    });
  }

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

  toggleTaskList(taskGroupId: number): void {
    const index = this.visibleTaskLists.indexOf(taskGroupId);
    if (index === -1) {
      this.visibleTaskLists.push(taskGroupId);
    } else {
      this.visibleTaskLists.splice(index, 1);
    }
  }

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

  goToUserProfile() {
    this.router.navigate(['api/user/me', this.userId]);
  }

  toggleNewTaskInput() {
    this.showNewTaskInput = !this.showNewTaskInput;
    if (!this.showNewTaskInput) {
      this.newGroup.name = '';
    }
  }

}
