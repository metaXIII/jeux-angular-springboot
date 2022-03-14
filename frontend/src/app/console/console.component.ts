import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GameService} from "../service/game.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-console',
  templateUrl: './console.component.html',
  styleUrls: ['./console.component.scss']
})
export class ConsoleComponent implements OnInit {
  public consoleForm: FormGroup = this.initForm()
  public error?: boolean


  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private gameService: GameService) {
  }

  ngOnInit(): void {
  }

  private initForm() {
    return this.formBuilder.group({
      name: ['', Validators.required]
    })
  }

  submit() {
    this.gameService.sendConsole(this.consoleForm).subscribe({
      complete: () => this.router.navigate(["index"])
    })
  }
}
