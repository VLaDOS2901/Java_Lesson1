import React, { useState } from 'react';
import Datepicker from "react-tailwindcss-datepicker";
  
const Calendar = () => {
    const [value, setValue] = useState({
        startDate: new Date(),
        endDate: new Date()
    });
    
    const handleValueChange = (newValue: any) => {
        console.log("newValue:", newValue);
        setValue(newValue);
    }
    return(
        <div>
        <Datepicker
            value={value}
            onChange={handleValueChange}
        />
    </div>
    )
}

export default Calendar;
