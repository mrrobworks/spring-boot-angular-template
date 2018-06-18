import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavTopBarComponent } from './nav-top-bar.component';

describe('NavTopBarComponent', () => {
  let component: NavTopBarComponent;
  let fixture: ComponentFixture<NavTopBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavTopBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavTopBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
