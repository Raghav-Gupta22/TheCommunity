import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReviewsComponent } from './add-reviews.component';

describe('AddReviewsComponent', () => {
  let component: AddReviewsComponent;
  let fixture: ComponentFixture<AddReviewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddReviewsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
