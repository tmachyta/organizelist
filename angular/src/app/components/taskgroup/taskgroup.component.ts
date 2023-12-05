import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-taskgroup',
  templateUrl: './taskgroup.component.html',
  styleUrl: './taskgroup.component.css'
})
export class TaskgroupComponent {
  @Input() firstName: string = '';
  @Input() lastName: string = '';
  selectedTaskGroup: any;

  onTaskGroupSelected(taskGroup: any) {
    this.selectedTaskGroup = taskGroup;
  }
}
