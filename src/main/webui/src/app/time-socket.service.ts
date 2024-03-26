import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TimeSocketService {

  private socket: WebSocket;

  data = new BehaviorSubject<any>(null);


  constructor() { 
    this.socket = new WebSocket('ws://127.0.0.1:8080/time/webui');

    this.socket.onopen = () => {
      console.log('WebSocket connection established.');
    };

    this.socket.onmessage = (event) => {
      console.log(event.data);
      this.data.next(event.data)
    }
  }
}
