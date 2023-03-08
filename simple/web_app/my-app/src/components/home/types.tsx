export interface ICategoryItem  {
    id: number;
    name: string;
    description: string;
    image: string;
}
//Визначаєио типи екшенів, які в нас будуть
export enum CategoryActionType {
    GET_CATEGORIES = "GET_CATEGORIES_ACTION"
}