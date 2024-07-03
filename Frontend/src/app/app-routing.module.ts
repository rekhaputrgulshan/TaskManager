import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTaskComponent } from './create-task/create-task.component';
import { UpdateTaskComponent } from './update-task/update-task.component';
import { DeleteTaskComponent } from './delete-task/delete-task.component';
import { TaskListComponent } from './task-list/task-list.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [

  {path: 'create-task', component: CreateTaskComponent},
  
  { path: 'signup', component: SignupComponent }, 
  { path: 'login', component: LoginComponent },

  {path: '', redirectTo: 'tasks', pathMatch: 'full'},
  {path: 'tasks', component: TaskListComponent},
  {path: 'update-task/:id', component:UpdateTaskComponent},
  {path: 'delete-task/:id', component: DeleteTaskComponent },

  { path: '**', redirectTo: 'tasks' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
