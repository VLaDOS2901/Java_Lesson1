import axios from "axios";
import { useEffect, useState } from "react";
import { Buffer } from 'buffer';
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { CategoryActionType, ICategoryItem } from "./types";
import { useDispatch } from "react-redux";
import { APP_ENV } from "../../env";
/*
  This example requires some changes to your config:
  
  ```
  // tailwind.config.js
  module.exports = {
    // ...
    plugins: [
      // ...
      require('@tailwindcss/aspect-ratio'),
    ],
  }
  ```
*/


const Home = () => {
  const [categories, setCategories] = useState([]);

  //Отримуємо всі категорії
  const dispatch = useDispatch();
  useEffect(() => {
    axios.get<Array<ICategoryItem>>(`${APP_ENV.REMOTE_HOST_NAME}api/categories`)
      .then(resp => {
        const { data } = resp;
        dispatch({ type: CategoryActionType.GET_CATEGORIES, payload: data });
        console.log("Server result", resp);
      });
  }, []);

  const deleteCategory = (id: number) => {
    console.log(id);
    dispatch(dispatch({ type: CategoryActionType.DELETE_CATEGORY, payload: id }))
    window.location.replace("/");
  }

  const list = useSelector((store: any) => store.categories as Array<ICategoryItem>);
  //Перебираємо кожну категорію в циклі
  const content = list.map((category) => (
    <div key={category.id} className="group relative">
      <div className="relative h-80 w-full overflow-hidden rounded-lg bg-white group-hover:opacity-75 sm:aspect-w-2 sm:aspect-h-1 sm:h-64 lg:aspect-w-1 lg:aspect-h-1">
        <img
          src={APP_ENV.REMOTE_HOST_NAME + "files/600_" + category.image}
          alt={category.name}
          className="h-full w-full object-cover object-center"
        />
      </div>
      <h3 className="text-sm text-gray-500">
        <span>
          {category.name}
        </span>
      </h3>
      <p className="text-base font-semibold text-gray-900">
        {category.description}
      </p>
      <button type="button" onClick={() => deleteCategory(category.id)} className="bg-red-500 hover:bg-red-600 text-white font-medium py-2 px-4 rounded-md mb-5">Delete</button>
    </div>
  ));
  return (
    <>
      <h2 className="text-2xl font-bold text-gray-900 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">Категорії</h2>
      {/* <ul className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 text-2xl">
        {list.map((c: any) =>
          <li key={c.id}>#{c.id}  {c.name}<img src={"http://localhost:8084/files/"+c.image} className="w-40 object-center" /></li>)
        }
      </ul> */}
      <div className="bg-gray-100">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="mx-auto max-w-2xl py-16 sm:py-24 lg:max-w-none lg:py-32">
            <h2 className="text-2xl font-bold text-gray-900">Collections</h2>

            <div className="py-2">
              <Link
                to="/categories/create"
                className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
              >
                Додати категорію
              </Link>
            </div>
            <div className="mt-6 space-y-12 lg:grid lg:grid-cols-3 lg:gap-x-6 lg:space-y-0">
              {content}
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Home;