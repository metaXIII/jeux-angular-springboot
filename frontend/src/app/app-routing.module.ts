import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {GameComponent} from "./game/game.component";
import {ConsoleComponent} from "./console/console.component";
import {ListComponent} from "./list/list.component";

const routes: Routes = [
  {path: "index", component: IndexComponent},
  {path: "game", component: GameComponent},
  {path: "game/:id", component: GameComponent},
  {path: "console", component: ConsoleComponent},
  {path: "list", component: ListComponent},
  {path: "**", component: IndexComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
