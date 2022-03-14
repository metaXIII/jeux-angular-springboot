import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Game} from "../model/game.model";
import {GameService} from "../service/game.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {
  public game?: Game

  constructor(private route: ActivatedRoute, private gameService: GameService, private router: Router) {
  }

  ngOnInit(): void {
    this.gameService.findCurrent().subscribe({
        next: (resp: Game) => {
          this.game = resp
        },
        error: err => console.log(err)
      }
    )
  }

  finished(id: number) {
    this.gameService.finished(id).subscribe({
      next: resp => console.log(resp),
      error: err => console.log(err),
      complete: () => {
        this.game = undefined
        this.router.navigate(['index'])
      }
    })
  }

  giveup(id: number) {
    this.gameService.giveUp(id).subscribe({
      next: resp => console.log(resp),
      error: err => console.log(err),
      complete: () => {
        this.game = undefined
        this.router.navigate(['index'])
      }
    })
  }
}
