import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TournamentComponent } from './components/tournament/tournament.component';
import { DashIfEmptyPipe } from './utils/pipes/dash-if-empty.pipe';

@NgModule({
  declarations: [
    AppComponent,
    TournamentComponent,
    DashIfEmptyPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
  ],
  exports: [
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
