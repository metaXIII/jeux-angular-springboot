import {Console} from "./console.model";

export class Game {
  constructor(public id: number,
              public name: string,
              public console: Console,
              public status: number) {
  }
}
