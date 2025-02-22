from pydantic import BaseModel, EmailStr
from enum import Enum

class RoleEnum(str, Enum):
    admin = "admin"
    instructor = "instructor"
    student = "student"

class UserCreate(BaseModel):
    username: str
    email: EmailStr
    password: str
    role: RoleEnum