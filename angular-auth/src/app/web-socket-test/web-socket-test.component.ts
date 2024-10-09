import {Component, OnInit, OnDestroy, Inject, PLATFORM_ID} from '@angular/core';
import { WebSocketService } from '../websocket.service';
import {CommonModule, isPlatformBrowser} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-web-socket-test',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './web-socket-test.component.html',
  styleUrls: ['./web-socket-test.component.css']
})
export class WebSocketTestComponent implements OnInit, OnDestroy {
  messages: string[] = [];
  newMessage: string = '';

  constructor(private webSocketService: WebSocketService,    @Inject(PLATFORM_ID) private platformId: object) {}

/*  ngOnInit(): void {
    this.webSocketService.connect('ws://localhost:8080/ws');

    this.webSocketService.getMessages().subscribe(message => {
      this.messages.push(message);
    });
  }*/
  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.webSocketService.connect('ws://localhost:8080/ws');
      this.webSocketService.getMessages().subscribe(message => {
        this.messages.push(message);
      });
    }
  }

  sendMessage() {
    if (this.newMessage.trim()) {
      this.webSocketService.sendMessage(this.newMessage);
      this.newMessage = '';
    }
  }

  ngOnDestroy(): void {
    this.webSocketService.disconnect();
  }
}
