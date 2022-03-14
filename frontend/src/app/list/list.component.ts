import {Component, OnInit} from '@angular/core';
import {GameService} from "../service/game.service";
import {Game} from "../model/game.model";
import {Status} from "../model/status.enum.model";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  public games?: [Game]

  constructor(private gameService: GameService) {
  }

  ngOnInit(): void {
    this.gameService.listGames().subscribe({
      next: (games: [Game]) => this.games = games,
      error: err => console.log(err)
    })
  }

  getStatus(code: number) {
    return this.getTranslation(Status[code])
  }

  private getTranslation(status: string): string {
    let message: string = "Non renseigné";
    switch (status) {
      case "NOT_STARTED":
        message = "Pas encore démarré"
        break
      case "IN_PROGRESS":
        message = "En cours"
        break
      case "FINISHED":
        message = "Fini"
        break
      case "GIVE_UP":
        message = "Abandonné"
        break
      case "INVALID":
        message = "Un soucis est survenu"
        break
    }
    return message
  }
}
