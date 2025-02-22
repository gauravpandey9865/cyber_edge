from sqlalchemy import Column, Integer, String, Enum as PgEnum
import enum
from database import Base

class RoleEnum(enum.Enum):
    admin = "admin"
    instructor = "instructor"
    student = "student"

class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(50), unique=True, index=True, nullable=False)
    email = Column(String(100), unique=True, index=True, nullable=False)
    hashed_password = Column(String(100), nullable=False)
    role = Column(PgEnum(RoleEnum), default=RoleEnum.student, nullable=False)