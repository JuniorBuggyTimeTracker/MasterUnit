import { Component } from '@angular/core';
import { Driver } from '../../utils/driver.interface';

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.scss']
})
export class TournamentComponent {

  driver: Driver[] = [
    {firstname: "Max", lastname: "Müller", racerId: "4711"},
    {firstname: "Williams", lastname: "Ernest", racerId: "123"},
    {firstname: "Cruz", lastname: "Henrika", racerId: "1545"},
    {firstname: "Stevens", lastname: "Posy", racerId: "12"},
    {firstname: "Payne", lastname: "Leonela", racerId: "3"},
    {firstname: "Evans", lastname: "Nikolett", racerId: "16"},
    {firstname: "Davis", lastname: "Christopher", racerId: "79"},
    {firstname: "Taylor", lastname: "Louella", racerId: "462"},
    {firstname: "Thomas", lastname: "Carl", racerId: "49"},
    {firstname: "Fisher", lastname: "Jenkins", racerId: "63"},
    {firstname: "Black", lastname: "Geraldine", racerId: "69"},
    {firstname: "Lee", lastname: "Jim", racerId: "127"},
  ]

  public deleteDriver (driverId: string) {

  }

  public editDriver (driver: Driver) {

  }
}
