import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routing } from './pages.routing';
import {ReactiveFormsModule } from '@angular/forms';
import {CustomMaterialModule} from '../material.module'


import { LayoutModule } from '../shared/layout.module';
import { SharedModule } from '../shared/shared.module';

/* components */
import { PagesComponent } from './pages.component';
import { LoginComponent } from './login/login.component';


@NgModule({
    imports: [
        CommonModule,
        LayoutModule,
        SharedModule,
        routing,
        ReactiveFormsModule,
        CustomMaterialModule
    ],
    declarations: [
        PagesComponent,
        LoginComponent
    ]
})
export class PagesModule { }
