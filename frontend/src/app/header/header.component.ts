import {Component, OnInit} from '@angular/core';
import {GameService} from "../service/game.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private gameService: GameService, private router: Router) {
  }

  ngOnInit(): void {
  }

  play() {
    this.gameService.play().subscribe(
      {
        error: err => console.log(err),
        complete: () => this.router.navigate(["index"])
      }
    )
  }
}
