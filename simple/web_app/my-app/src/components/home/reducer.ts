import axios from 'axios';
import { ICategoryItem, CategoryActionType } from './types';

const initState: Array<ICategoryItem> = [
    // {
    //     id: 1,
    //     name: "Сало",
    //     description: "Смачно і корисно",
    //     image: "https://i1.sndcdn.com/artworks-5VqsfUyoy56Den75-KI2mAw-t500x500.jpg"
    // }
];
//Визначає тип екшена на який виконується запит і виконує відповідну дію
export const CategoryReducer = (state = initState, action: any) => {
    console.log("action", action);
    switch (action.type) {
        case CategoryActionType.GET_CATEGORIES: {
            const payload: Array<ICategoryItem> = action.payload as [];
            return payload;
        }
        case CategoryActionType.POST_CATEGORY: {
            axios.post('http://localhost:8084/api/categories', action.payload)
                .then(response => {                   
                    console.log('Form data submitted successfully:', response.data);
                })
                .catch(error => {
                    console.error('Error submitting form data:', error);
                });
        }
    }
    return state;
}