export class CreateTaskRequest {
  name: string = '';
  description: string = '';
  priority: string = '';
  status: string = '';
  orderDate: string = '';
  taskGroupId: number = 0;
  userId: number = 0;
}
