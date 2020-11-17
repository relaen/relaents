import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Area} from './area'
import {DeviceModel} from './devicemodel'

@Entity("t_provider",'tunnel')
export class Provider extends BaseEntity{
	@Id()
	@Column({
		name:'provider_id',
		type:'int',
		nullable:false
	})
	private providerId:number;

	@ManyToOne({entity:'Area',eager:false})
	@JoinColumn({name:'area_id',refName:'area_id'})
	private area:Area;

	@Column({
		name:'provider_name',
		type:'string',
		nullable:true
	})
	private providerName:string;

	@Column({
		name:'contact_man',
		type:'string',
		nullable:true
	})
	private contactMan:string;

	@Column({
		name:'manager',
		type:'string',
		nullable:true
	})
	private manager:string;

	@Column({
		name:'manager_idno',
		type:'string',
		nullable:true
	})
	private managerIdno:string;

	@Column({
		name:'tel',
		type:'string',
		nullable:true
	})
	private tel:string;

	@Column({
		name:'mobile',
		type:'string',
		nullable:true
	})
	private mobile:string;

	@Column({
		name:'address',
		type:'string',
		nullable:true
	})
	private address:string;

	@Column({
		name:'zipcode',
		type:'string',
		nullable:true
	})
	private zipcode:string;

	@Column({
		name:'nation',
		type:'string',
		nullable:true
	})
	private nation:string;

	@OneToMany({entity:'DeviceModel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'provider',eager:false})
	private deviceModels:Array<DeviceModel>;

	public getProviderId():number{
		return this.providerId;
	}
	public setProviderId(value:number){
		this.providerId = value;
	}

	public getArea():Area{
		return this.area;
	}
	public setArea(value:Area){
		this.area = value;
	}

	public getProviderName():string{
		return this.providerName;
	}
	public setProviderName(value:string){
		this.providerName = value;
	}

	public getContactMan():string{
		return this.contactMan;
	}
	public setContactMan(value:string){
		this.contactMan = value;
	}

	public getManager():string{
		return this.manager;
	}
	public setManager(value:string){
		this.manager = value;
	}

	public getManagerIdno():string{
		return this.managerIdno;
	}
	public setManagerIdno(value:string){
		this.managerIdno = value;
	}

	public getTel():string{
		return this.tel;
	}
	public setTel(value:string){
		this.tel = value;
	}

	public getMobile():string{
		return this.mobile;
	}
	public setMobile(value:string){
		this.mobile = value;
	}

	public getAddress():string{
		return this.address;
	}
	public setAddress(value:string){
		this.address = value;
	}

	public getZipcode():string{
		return this.zipcode;
	}
	public setZipcode(value:string){
		this.zipcode = value;
	}

	public getNation():string{
		return this.nation;
	}
	public setNation(value:string){
		this.nation = value;
	}

	public getDeviceModels():Array<DeviceModel>{
		return this.deviceModels;
	}
	public setDeviceModels(value:Array<DeviceModel>){
		this.deviceModels = value;
	}

}