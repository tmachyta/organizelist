import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskgrouplistComponent } from './taskgrouplist.component';

describe('TaskgrouplistComponent', () => {
  let component: TaskgrouplistComponent;
  let fixture: ComponentFixture<TaskgrouplistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskgrouplistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TaskgrouplistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
