import axios from "axios";

export interface ICategoryItem  {
    id: number;
    name: string;
    description: string;
    image: string;
}
export interface ICategoryItemCreate  {
    name: string;
    description: string;
    image: string;
}
//Визначаєио типи екшенів, які в нас будуть
export enum CategoryActionType {
    GET_CATEGORIES = "GET_CATEGORIES_ACTION",
    POST_CATEGORY = "POST_CATEGORY_ACTION"
}
