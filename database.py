from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# PostgreSQL connection string format:
# postgresql+psycopg2://user:password@host:port/database
DATABASE_URL = "postgresql+psycopg2://postgres:root123@localhost/cyber_edge"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()