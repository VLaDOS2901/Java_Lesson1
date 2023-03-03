import axios from "axios";
import { ChangeEvent, useState } from "react";

const Create = () => {
    interface Category{
        name: string;
        description: string;
        base64: string;
    }
    const [file, setFile] = useState<File | null>(null);
    const [previewUrl, setPreviewUrl] = useState<string | null>(null);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        const selectedFile = event.target.files?.[0];
        if (selectedFile) {
            setFile(selectedFile);

            const reader = new FileReader();
            reader.onload = () => {
                setPreviewUrl(reader.result as string);
                //console.log(reader.result);
            };
            reader.readAsDataURL(selectedFile);
        } else {
            setFile(null);
            setPreviewUrl(null);
        }
        console.log(previewUrl);
    }
    function nameInputChange(event: ChangeEvent<HTMLInputElement>) {
        setName(event.target.value);
      }
      function descriptionInputChange(event: ChangeEvent<HTMLInputElement>) {
        setDescription(event.target.value);
      }
    function createCategory(){
        console.log(previewUrl);
        console.log(name);
        console.log(description);
        const category: Category = {
            name: name,
            description: description,
            base64: previewUrl as string
        };
        axios.post('http://localhost:8084/api/categories', category)
        .then(response => {
          console.log('Form data submitted successfully:', response.data);
          // handle success response
        })
        .catch(error => {
          console.error('Error submitting form data:', error);
          // handle error response
        });
        window.location.replace("/");
    }
    return (
        <>
            <div className="isolate bg-white py-24 px-6 sm:py-32 lg:px-8">
                <div /*action="#" method="POST"*/ className="mx-auto mt-16 max-w-xl sm:mt-20">
                    <div className="grid grid-cols-1 gap-y-6 gap-x-8 sm:grid-cols-2">

                        <div className="sm:col-span-2">
                            <label className="block text-sm font-semibold leading-6 text-gray-900">Name</label>
                            <div className="mt-2.5">
                                <input type="text" onChange={nameInputChange} name="name" id="name" className="block w-full rounded-md border-0 py-2 px-3.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                            </div>
                        </div>
                        <div className="sm:col-span-2">
                            <label className="block text-sm font-semibold leading-6 text-gray-900">Description</label>
                            <div className="mt-2.5">
                                <input type="text" onChange={descriptionInputChange} name="description" id="description" className="block w-full rounded-md border-0 py-2 px-3.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                            </div>
                        </div>
                        <div className="sm:col-span-2">
                            <label className="block text-sm font-semibold leading-6 text-gray-900">Image</label>
                            <div className="mt-2.5">
                                <input type="file" onChange={handleInputChange} accept=".jpg,.jpeg,.png" name="image" id="image" className="block w-full rounded-md border-0 py-2 px-3.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                {previewUrl && (
                                    <img src={previewUrl} alt="Preview" />
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="mt-10">
                        <button onClick={createCategory} className="block w-full rounded-md bg-indigo-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Create</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Create;