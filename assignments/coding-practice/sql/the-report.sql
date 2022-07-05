select if(Grade < 8, NULL, Name), Grade, Marks from
Students join Grades
where Marks between Min_Mark and Max_Mark
order by Grade desc, Name asc;