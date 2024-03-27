import { Component } from '@angular/core';
import { TimeSocketService } from './time-socket.service';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'JuniorBuggyTimeTracker';

  tournamentReady: boolean = false;
  data: any;

  links = [
    { title: 'Tunier', fragment: 'tournament' },
    { title: 'Rennen', fragment: 'races' },
    { title: 'Läufe', fragment: 'heats' }
  ];

  constructor(timeService: TimeSocketService, public route: ActivatedRoute) {
    timeService.data.subscribe(data => {
      this.data = data;
      console.log("Sachen aus der Component: " + data)
    })
  }
}
