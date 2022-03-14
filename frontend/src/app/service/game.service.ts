import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Game} from "../model/game.model";
import {Console} from "../model/console.model";
import {FormGroup} from "@angular/forms";

@Injectable()
export class GameService {

  constructor(private httpClient: HttpClient) {
  }

  public findCurrent = () => {
    return this.httpClient.get<Game>("service/game")
  }

  public finished = (id: number) => {
    return this.httpClient.get("service/finished/" + id)
  }

  public giveUp = (id: number) => {
    return this.httpClient.get("service/giveup/" + id)
  }

  play = () => {
    return this.httpClient.get("service/play")
  }

  listConsole = () => {
    return this.httpClient.get<[Console]>("service/listConsole")
  }

  sendGame = (gameForm: FormGroup) => {
    const game = {
      name: gameForm.value["name"],
      console: gameForm.value["console"]
    }
    return this.httpClient.post<Game>("service/game", game)
  }

  sendConsole = (consoleForm: FormGroup) => {
    const console = {
      name: consoleForm.value["name"]
    }
    return this.httpClient.post<Console>("service/console", console)
  }

  listGames = () => {
    return this.httpClient.get<[Game]>("service/listGames")
  }
}
