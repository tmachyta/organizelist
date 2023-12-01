import {TaskDto} from "../task/task-dto";

export class TaskGroupDto {
  id: number = 0;
  name: string = '';
  tasks: TaskDto[] = [];
}
