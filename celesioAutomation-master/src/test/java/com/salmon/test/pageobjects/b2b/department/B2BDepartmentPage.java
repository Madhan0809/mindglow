package com.salmon.test.pageobjects.b2b.department;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class B2BDepartmentPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BDepartmentPage.class);
    Actions action;
    private By departmentElements = By.cssSelector("ul #categoriesColumn li a span");
    private By bestPromotions = By.cssSelector(".panel>.carouselInner");
    private By eSpotHeader = By.cssSelector(".row.espotHeader");
    private By eSpotCentre=By.cssSelector(".left_espot.centered");
//    private By facetEspot=By.cssSelector("div.row.categoryNavWidget.facetContainer img");
    private By leftArrow = By.cssSelector("div.carousel.recentlyViewed>a.carouselLeft");
    private By rightArrow = By.cssSelector("div.carousel.recentlyViewed>a.carouselRight");
    private By bestSellerImg = By.cssSelector("a[id^='CatalogEntryDisplayLink']>img");
    private By categoryCarouselWidget = By.cssSelector(".departmentCarousel.carousel.categoryWidget");
    //Facet navigation
    private By carousePanel = By.cssSelector(".departmentCarousel.carousel.categoryWidget");
    private By carouseItems = By.cssSelector("a[id^='SubCategory_links']");
    private By toggleIcon = By.cssSelector("div[id^='facet_nav_collapsible']");
    private By facetItem = By.cssSelector(".facetCountContainer:not(id)");

	public void onHoverOfTargetDepartment(String departmentName) {
		action = new Actions(getWebDriver());
		List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
		for (WebElement element : departmentElementsList) {
			LOG.info("elements in the list hover ::::: ->" + element.getText());
			if (element.getText().equalsIgnoreCase(departmentName)) {
				action.moveToElement(element).build().perform();
			}
		}
	}

	public boolean isDepartmentPageDisplayedCorrectly(String departmentTitle) {
		if (!isElementPresent(categoryCarouselWidget)) {
			LOG.error("The category carouse widget is not displayed.");
			return false;
		}
		if (!getCurrentPageTitle().equals(departmentTitle)) {
			LOG.error("Page title is not correct. Actual title is " + getCurrentPageTitle());
			return false;
		}
		return true;
	}

	public boolean isBestPromotionsDisplayed() {
		return isElementPresent(bestPromotions);
	}

	public boolean isCarouselDisplayed() {
		return isElementPresent(carousePanel);
	}

	public boolean isEspotDisplayed() {
		if (!isElementPresent(eSpotHeader) || !isElementPresent(eSpotCentre)) {
			LOG.error("Not all the eSpots are not shown in department page.");
			return false;
		}
		return true;
	}

	public void onClickOfTargetDepartment(String departmentName) {
		action = new Actions(getWebDriver());
		List<WebElement> departmentList = this.visibilityOfAllElementsLocatedBy(departmentElements);
		if (!departmentList.isEmpty()) {
			for (WebElement dp : departmentList) {
				if (dp.getText().trim().equalsIgnoreCase(departmentName)) {
					action.moveToElement(dp).click().perform();
					break;
				}
			}
		} else {
			LOG.error("The department: " + departmentName + " is not found");
		}
	}

	public void clickArrowsFromBestSellers(String arrowNavigation) {
		if (arrowNavigation.equalsIgnoreCase("Left")) {
			waitForElementAvailableAndVisible(leftArrow).click();
		} else if (arrowNavigation.equalsIgnoreCase("Right")) {
			waitForElementAvailableAndVisible(rightArrow).click();
		} else
			LOG.error("Cannot find the target arrow: " + arrowNavigation);
	}

	public boolean isAllImagesInSubCategoryDisplayed() {
		if (!isBestPromotionsDisplayed()) {
			return false;
		}
		List<WebElement> subImages = getImagesOfBestSellersInCurrentView();
		if (subImages.size() == 0) {
			LOG.error("No image is displayed in best seller.");
			return false;
		} else {
			for (WebElement img : subImages) {
				if (!img.isDisplayed()) {
					return false;
				}
			}
			return true;
		}
	}

	public List<WebElement> getImagesOfBestSellersInCurrentView() {
		List<WebElement> allBestSellerImages = getWebDriver().findElements(bestSellerImg);
		List<WebElement> currentSubImgs = new ArrayList<>();
		for (int i = 0; i < allBestSellerImages.size(); i++) {
			if (allBestSellerImages.get(i).isDisplayed()) {
				currentSubImgs.add(getWebDriver().findElements(bestSellerImg).get(i));
			}
		}
		return currentSubImgs;
	}

	public boolean isArrowDisplayed(String arrowNavigation) {
		if (arrowNavigation.equalsIgnoreCase("Left")) {
			return this.isElementPresent(leftArrow);
		} else if (arrowNavigation.equalsIgnoreCase("Right")) {
			return this.isElementPresent(rightArrow);
		} else {
			LOG.error("arrow navigation is not found: " + arrowNavigation);
			return false;
		}
	}

	public void clickOnProductFromCarousel(String productName) {
		List<WebElement> allCarousItems = visibilityOfAllElementsLocatedBy(carouseItems);
		for (WebElement item : allCarousItems) {
			if (item.getAttribute("title").trim().equals(productName)) {
				item.click();
				break;
			}
			LOG.error("Product is not found. There is no product of " + productName);
		}
	}

    public void clickOnProductFromBestSellers(String productName) {
        List<WebElement> allBestSellersItems = getWebDriver().findElements(bestSellerImg);
        if (productName.startsWith("random")) {
            allBestSellersItems.get(0).click();
        } else {
            for (WebElement item : allBestSellersItems) {
                String actualProductName = item
                        .findElement(By
                                .xpath("./ancestor::div[@class='product row']/div[@class='productContent']/a[@class]"))
                        .getText();
                if (actualProductName.trim().equals(productName)) {
                    item.click();
                    break;
                }
                LOG.error("Product is not found. There is no product of " + productName);
            }
        }
    }

	private String getCategoryToggleStatus() {
		String status = this.waitForElementAvailableAndVisible(toggleIcon).getAttribute("class");
		if (status.contains("collapsibleContainer open")) {
			LOG.info("The toggle icon is in closed status now.");
			return getB2bProp("toggle.collapsed");
		} else {
			LOG.info("The toggle icon is in opened status now.");
			return getB2bProp("toggle.elapsed");
		}
	}

	public void clickOnToggleIcon(String toggleOption) {
		if (toggleOption.equalsIgnoreCase(getCategoryToggleStatus())) {
			LOG.info("The toggle icon is already in '" + toggleOption + "' status.");
		} else {
			waitForExpectedElement(toggleIcon).findElement(By.cssSelector("h3")).click();
		}
	}

	public boolean canDisplayAllCatagoriesInFacetNavigation() {
		List<WebElement> facetCounts = getWebDriver().findElements(facetItem);
		for (int i = 0; i < facetCounts.size(); i++) {
			if (!facetCounts.get(i).isDisplayed() && i == facetCounts.size() - 1) {
				return false;
			}
		}
		return true;
	}

	public int getSubCategoryCountFromFacet() {
		return this.visibilityOfAllElementsLocatedBy(facetItem).size();
	}

	public void selectSubCategoryFromFacet(String subCategoryName) {
		List<WebElement> allFacetItems = visibilityOfAllElementsLocatedBy(facetItem);
		for (WebElement item : allFacetItems) {
			if (item.getText().trim().contains(subCategoryName)) {
				item.click();
				break;
			}
		}
	}

}
