import React from "react";

interface HighlightTextProps {
  text: string;
  searchText: string;
}

export const HighlightText: React.FC<HighlightTextProps> = ({ text, searchText }) => {
  if (!searchText) return <span>{text}</span>;

  const validText = text ? text.toString() : '';

  const regex = new RegExp(`(${searchText})`, 'gi');

  const parts: string[] = validText.split(regex);

  return (
    <span>
      {parts.map((part, index) =>
        part.toLowerCase() === searchText.toLowerCase() ? (
          <span key={index} style={{ backgroundColor: "#ffc069", padding: "0" }}>
            {part}
          </span>
        ) : (
          part
        )
      )}
    </span>
  );
};