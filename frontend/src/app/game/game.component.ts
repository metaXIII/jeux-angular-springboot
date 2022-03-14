import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Console} from "../model/console.model";
import {GameService} from "../service/game.service";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  public gameForm: FormGroup = this.initForm()
  public consoleList?: [Console]
  public error?: boolean

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private gameService: GameService) {
  }

  ngOnInit(): void {
    this.gameService.listConsole().subscribe(
      {
        next: (resp: [Console]) => {
          this.consoleList = resp
        },
        error: err => console.log(err)
      }
    )
  }

  private initForm() {
    return this.formBuilder.group({
      name: ['', Validators.required],
      console: ['', Validators.required]
    })
  }

  submit() {
    this.gameService.sendGame(this.gameForm).subscribe({
      complete: () => this.router.navigate(["index"])
    })
  }
}
