import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TournamentComponent } from './components/tournament/tournament.component';

const routes: Routes = [
  { path: '', redirectTo: '/tournament', pathMatch: 'full' },
  { path: 'tournament', component: TournamentComponent},
  { path: 'races', component: TournamentComponent},
  { path: 'heats', component: TournamentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
