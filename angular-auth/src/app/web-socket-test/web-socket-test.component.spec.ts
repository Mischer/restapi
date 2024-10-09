import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebSocketTestComponent } from './web-socket-test.component';

describe('WebSocketTestComponent', () => {
  let component: WebSocketTestComponent;
  let fixture: ComponentFixture<WebSocketTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WebSocketTestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WebSocketTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
